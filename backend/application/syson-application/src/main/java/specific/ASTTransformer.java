/*******************************************************************************
 * Copyright (c) 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package specific;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.components.emf.services.JSONResourceFactory;

import org.eclipse.syson.sysml.Classifier;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.Feature;
import org.eclipse.syson.sysml.FeatureDirectionKind;
import org.eclipse.syson.sysml.FeatureMembership;
import org.eclipse.syson.sysml.FeatureTyping;
import org.eclipse.syson.sysml.FlowConnectionUsage;
import org.eclipse.syson.sysml.Namespace;
import org.eclipse.syson.sysml.Redefinition;
import org.eclipse.syson.sysml.Relationship;
import org.eclipse.syson.sysml.Subclassification;
import org.eclipse.syson.sysml.Subsetting;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.sysml.Type;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ASTTransformer.
 * 
 * @author gescande.
 */
public class ASTTransformer {

    private static final String CHILDREN_CONST = "children";
    private static final String TARGET_CONST = "target";
    private static final String TARGET_REF_CONST = "targetRef";
    private static final String HERITAGE_CONST = "heritage";
    private static final String QUOTE_CONST = "\'";
    private static final String TEXT_CONST = "text";
    
    private Map<EObject, String> targetRefToResolve = new HashMap<>();
    private Map<String, EObject> objectList = new HashMap<>();
    private List<String> importRegexp = new ArrayList<>();
    
    
    public Resource convertResource(InputStream input, Map<String, EObject> previousObjectList) {

        objectList.putAll(previousObjectList);

        Resource result = new JSONResourceFactory().createResource(new JSONResourceFactory().createResourceURI("test"));
        
        ObjectMapper objectMapper = new ObjectMapper();

        // Lire le fichier JSON et le mapper à un objet JsonNode
        try {
            JsonNode astJson = objectMapper.readTree(input);

            List<EObject> roots = mapJsonNode(astJson.get(CHILDREN_CONST).get(0).get(TARGET_CONST), null);

            resolveReferences();
            
            result.getContents().addAll(roots);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public List<EObject> mapJsonNode(JsonNode mainNode, EObject parent) {
        final List<EObject> result = new ArrayList<>();
        if (mainNode.isArray()) {
            mainNode.forEach(t -> {
                result.addAll(mapJsonNode(t, parent));                
            });
        } else if (mainNode.has("$type")) {
            String type = mainNode.findValue("$type").textValue();

            EClassifier classif = SysmlPackage.eINSTANCE.getEClassifier(type);
            EClassImpl eclassImpl = (EClassImpl) classif;

            if (eclassImpl != null) {

                EObject instance = null;
                switch (eclassImpl.getName()) {
                    case "Namespace":
                        instance = namespaceMapping(eclassImpl, mainNode, parent);
                        break;
                    case "FlowConnectionUsage":
                        instance = flowConnectionUsageMapping(eclassImpl, mainNode, parent);
                        break;
                    case "FeatureTyping":
                        instance = featureTypingMapping(eclassImpl, mainNode, parent);
                        break;
                    case "Redefinition":
                        instance = redefinitionMapping(eclassImpl, mainNode, parent);
                        break;
                    case "Subsetting":
                        instance = getSubsettingMapping(eclassImpl, mainNode, parent);
                        break;
                    case "Subclassification":
                        instance = getSubclassificationMapping(eclassImpl, mainNode, parent);
                        break;
                    default:
                        instance = genericMapping(eclassImpl, mainNode, parent);

                }

                // Manage imports
                if (SysmlPackage.eINSTANCE.getImport().isSuperTypeOf(eclassImpl)) {
                    addImportValue(instance, mainNode);
                }
                result.add(instance);

            } else {

                for (JsonNode children : mainNode.findValues("children")) {
                    result.addAll(mapJsonNode(children, parent));
                }
                
                for (JsonNode target : mainNode.findValues(TARGET_CONST)) {
                    result.addAll(mapJsonNode(target, parent));
                }
                
                System.out.println("La classe Ecore pour le type " + type + " n'a pas été trouvée.");
            }

        } else {
            System.out.println("L'objet " + mainNode + " n'a pas de $type");
        }

        return result;
    }

    public EObject genericMapping(EClassImpl eclassImpl, JsonNode mainNode, EObject parent) {
        // Créez une instance de la classe Ecore
        EObject eObject = EcoreUtil.create(eclassImpl);
        String name = mainNode.get("$meta").get("qualifiedName").asText().replace(QUOTE_CONST, "");
        if (name.isBlank()) {
            name = mainNode.get("$meta").get("elementId").asText().replace(QUOTE_CONST, "");
        }
        objectList.put(name, eObject);
        // Vérifiez si la classe a été trouvée
        genericAttributeMapping(eObject, eclassImpl, mainNode);

        if (SysmlPackage.eINSTANCE.getRelationship().isSuperTypeOf(eObject.eClass())) {
            if (mainNode.has(CHILDREN_CONST)) {
                List<EObject> children = mapJsonNode(mainNode.get(CHILDREN_CONST), eObject);
                List<Relationship> relationship = children.stream().filter(t -> SysmlPackage.eINSTANCE.getRelationship().isSuperTypeOf(t.eClass())).map(t -> (Relationship) t).toList();
                relationship.forEach(t -> t.setOwningRelationship((Relationship) eObject));
                List<Element> element = children.stream().filter(t -> SysmlPackage.eINSTANCE.getElement().isSuperTypeOf(t.eClass())).map(t -> (Element) t).toList();
                element.forEach(t -> t.setOwningRelationship((Relationship) eObject));
            }
            if (mainNode.has(HERITAGE_CONST)) {
                List<EObject> heritage = mapJsonNode(mainNode.get(HERITAGE_CONST), eObject);
                List<Relationship> relationship = heritage.stream().filter(t -> SysmlPackage.eINSTANCE.getRelationship().isSuperTypeOf(t.eClass())).map(t -> (Relationship) t).toList();
                relationship.forEach(t -> t.setOwningRelationship((Relationship) eObject));
                List<Element> element = heritage.stream().filter(t -> SysmlPackage.eINSTANCE.getElement().isSuperTypeOf(t.eClass())).map(t -> (Element) t).toList();
                element.forEach(t -> t.setOwningRelationship((Relationship) eObject));
            }
            if (mainNode.has(TARGET_CONST)) {
                List<EObject> target = mapJsonNode(mainNode.get(TARGET_CONST), eObject);
                List<Relationship> relationship = target.stream().filter(t -> SysmlPackage.eINSTANCE.getRelationship().isSuperTypeOf(t.eClass())).map(t -> (Relationship) t).toList();
                relationship.forEach(t -> t.setOwningRelationship((Relationship) eObject));
                List<Element> element = target.stream().filter(t -> SysmlPackage.eINSTANCE.getElement().isSuperTypeOf(t.eClass())).map(t -> (Element) t).toList();
                element.forEach(t -> t.setOwningRelationship((Relationship) eObject));
            }
        } else if (SysmlPackage.eINSTANCE.getElement().isSuperTypeOf(eObject.eClass())) {
            if (mainNode.has(CHILDREN_CONST)) {
                List<EObject> children = mapJsonNode(mainNode.get(CHILDREN_CONST), eObject);
                List<Relationship> relationship = children.stream().filter(t -> SysmlPackage.eINSTANCE.getRelationship().isSuperTypeOf(t.eClass())).map(t -> (Relationship) t).toList();
                relationship.forEach(t -> t.setOwningRelatedElement((Element) eObject));
            }
            if (mainNode.has(HERITAGE_CONST)) {
                List<EObject> heritage = mapJsonNode(mainNode.get(HERITAGE_CONST), eObject);
                List<Relationship> relationship = heritage.stream().filter(t -> SysmlPackage.eINSTANCE.getRelationship().isSuperTypeOf(t.eClass())).map(t -> (Relationship) t).toList();
                relationship.forEach(t -> t.setOwningRelatedElement((Element) eObject));
            }
            if (mainNode.has(TARGET_CONST)) {
                List<EObject> target = mapJsonNode(mainNode.get(TARGET_CONST), eObject);
                List<Relationship> relationship = target.stream().filter(t -> SysmlPackage.eINSTANCE.getRelationship().isSuperTypeOf(t.eClass())).map(t -> (Relationship) t).toList();
                relationship.forEach(t -> t.setOwningRelatedElement((Element) eObject));
            }
        } else {
            System.out.println("Other");
        }

        return eObject;
    }

    public void genericAttributeMapping(EObject eobject, EClassImpl eclassImpl, JsonNode mainNode) {
        for (EAttribute attribute : eclassImpl.getEAllAttributes()) {
            String mappedName = attribute.getName();
            
            try {
                if (!mainNode.has(mappedName)) {
                    continue;
                } else {
                    switch (attribute.getEType().getName()) {
                        case "EDouble":
                            eobject.eSet(attribute, mainNode.get(mappedName).asDouble());
                            break;
                        case "EInteger":
                            eobject.eSet(attribute, mainNode.get(mappedName).asInt());
                            break;
                        case "EBoolean":
                            eobject.eSet(attribute, mainNode.get(mappedName).asBoolean()); 
                            break;
                        case "EString":
                            eobject.eSet(attribute, mainNode.get(mappedName).asText().replace(QUOTE_CONST, ""));
                            break;
                        case "FeatureDirectionKind":
                            eobject.eSet(attribute, FeatureDirectionKind.getByName(mainNode.get(mappedName).asText().replace(QUOTE_CONST, "").toUpperCase()));
                            break;
                        default:
                            System.out.println("Type inconnue " + attribute.getEType().getName() + " " + mappedName + " " + mainNode.get(mappedName));
                            break;
                    }
                }
            } catch (IllegalArgumentException e) {
                //System.out.println("IllegalArgumentException Attribut " + eclassImpl.getName() + "::" + mappedName + " non changeable");
            }
        }
    }

    public EObject namespaceMapping(EClassImpl eclassImpl, JsonNode mainNode, EObject parent) {
        
        Namespace eObject = (Namespace) genericMapping(eclassImpl, mainNode, parent);

        if (eObject.getDeclaredName() == null || eObject.getDeclaredName().isEmpty()) {
            eObject.setDeclaredName("ROOT");
        }

        return eObject;
    }

    public EObject flowConnectionUsageMapping(EClassImpl eclassImpl, JsonNode mainNode, EObject parent) {

        FlowConnectionUsage eObject = (FlowConnectionUsage) genericMapping(eclassImpl, mainNode, parent);

        List<FeatureMembership> featureMembership = mapJsonNode(mainNode.get("ends"), eObject).stream().map(t -> (FeatureMembership) t).toList();
        featureMembership.forEach(t -> t.setOwningRelationship(eObject));

        return eObject;
    }

    public EObject redefinitionMapping(EClassImpl eclassImpl, JsonNode mainNode, EObject parent) {

        Redefinition eObject = (Redefinition) genericMapping(eclassImpl, mainNode, parent);

        targetRefToResolve.put(eObject, findKeyReference(mainNode));
        eObject.setRedefiningFeature((Feature) parent);
        eObject.setDeclaredName(mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, ""));

        return eObject;
    }

    public EObject featureTypingMapping(EClassImpl eclassImpl, JsonNode mainNode, EObject parent) {

        FeatureTyping eObject = (FeatureTyping) genericMapping(eclassImpl, mainNode, parent);

        targetRefToResolve.put(eObject, findKeyReference(mainNode));
        eObject.setTypedFeature((Feature) parent);
        eObject.setDeclaredName(mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, ""));

        return eObject;
    }

    public EObject getSubsettingMapping(EClassImpl eclassImpl, JsonNode mainNode, EObject parent) {

        Subsetting eObject = (Subsetting) genericMapping(eclassImpl, mainNode, parent);

        targetRefToResolve.put(eObject, findKeyReference(mainNode));
        eObject.setSubsettingFeature((Feature) parent);
        eObject.setDeclaredName(mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, ""));

        return eObject;
    }

    public EObject getSubclassificationMapping(EClassImpl eclassImpl, JsonNode mainNode, EObject parent) {

        Subclassification eObject = (Subclassification) genericMapping(eclassImpl, mainNode, parent);

        targetRefToResolve.put(eObject, findKeyReference(mainNode));
        eObject.setSuperclassifier((Classifier) parent);
        eObject.setDeclaredName(mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, ""));

        return eObject;
    }

    public void resolveReferences() {
        for (var targetRef : targetRefToResolve.entrySet()) {
            EObject key = targetRef.getKey();
            if (SysmlPackage.eINSTANCE.getFeatureTyping().isSuperTypeOf(key.eClass())) {
                FeatureTyping eobject = (FeatureTyping) key;
                String referenceQualifiedName = targetRef.getValue();
                System.out.println("resolveReferences - FeatureTyping = " + referenceQualifiedName + " - " + objectList.get(referenceQualifiedName));
                Type target = (Type) objectList.get(referenceQualifiedName);

                eobject.setType(target);
            }
            if (SysmlPackage.eINSTANCE.getRedefinition().isSuperTypeOf(key.eClass())) {
                Redefinition eobject = (Redefinition) key;
                String referenceQualifiedName = targetRef.getValue();
                Feature target = (Feature) objectList.get(referenceQualifiedName);

                eobject.setRedefinedFeature(target);
            }
            if (SysmlPackage.eINSTANCE.getSubsetting().isSuperTypeOf(key.eClass())) {
                Subsetting eobject = (Subsetting) key;
                String referenceQualifiedName = targetRef.getValue();
                Feature target = (Feature) objectList.get(referenceQualifiedName);

                eobject.setSubsettedFeature(target);
            }
            if (SysmlPackage.eINSTANCE.getSubclassification().isSuperTypeOf(key.eClass())) {
                Subclassification eobject = (Subclassification) key;
                String referenceQualifiedName = targetRef.getValue();
                Classifier target = (Classifier) objectList.get(referenceQualifiedName);

                eobject.setSubclassifier(target);
            }
        }
    }

    void addImportValue(EObject eObject, JsonNode mainNode) {
        if (SysmlPackage.eINSTANCE.getNamespaceImport().isSuperTypeOf(eObject.eClass())) {
            importRegexp.add(mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, "") + ".*");
        }
        if (SysmlPackage.eINSTANCE.getMembershipImport().isSuperTypeOf(eObject.eClass())) {
            importRegexp.add(mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, ""));
        }
    }

    public String findKeyReference(JsonNode mainNode) {
        String reference = null;

        System.out.println("findKeyReference = " + mainNode.get(TARGET_REF_CONST).get("reference").asText().replace(QUOTE_CONST, "") + " - " + mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, ""));

        if (mainNode.get(TARGET_REF_CONST).has("reference") && !mainNode.get(TARGET_REF_CONST).get("reference").isNull()) {
            reference =  mainNode.get(TARGET_REF_CONST).get("reference").asText().replace(QUOTE_CONST, "");
        } else if (mainNode.get(TARGET_REF_CONST).has(TEXT_CONST) && !mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).isNull()) {
            String text = mainNode.get(TARGET_REF_CONST).get(TEXT_CONST).asText().replace(QUOTE_CONST, "");
            //Static import
            for (String importString : importRegexp) {
                System.out.println("Static import - importString = " + importString);
                if (importString.endsWith(text)) {
                    reference = importString;
                }
            }
            if (reference == null) {
                // Dynamic import
                for (String importString : importRegexp) {
                    if (importString.contains("*")) {
                        System.out.println("Dynamic import - importString = " + importString);
                        String regexp = "^" + importString + text + "$";
                        for (String key : objectList.keySet().stream().sorted((o1, o2) -> o1.length() - o2.length()).toList()) {
                            if (Pattern.compile(regexp).matcher(key).find()) {
                                reference = key;
                                break;
                            }
                        }
                        if (reference != null) {
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("findKeyReference - reference = " + reference);

        return reference;
    }
}
