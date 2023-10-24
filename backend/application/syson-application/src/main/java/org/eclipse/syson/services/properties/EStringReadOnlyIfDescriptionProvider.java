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
package org.eclipse.syson.services.properties;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.sirius.components.compatibility.emf.properties.EStructuralFeatureLabelProvider;
import org.eclipse.sirius.components.compatibility.emf.properties.PropertiesDefaultDescriptionProvider;
import org.eclipse.sirius.components.compatibility.emf.properties.api.IPropertiesValidationProvider;
import org.eclipse.sirius.components.compatibility.forms.WidgetIdProvider;
import org.eclipse.sirius.components.forms.description.IfDescription;
import org.eclipse.sirius.components.forms.description.LabelDescription;
import org.eclipse.sirius.components.representations.VariableManager;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Provides the custom SysMLv2 description of the widget to use to support the Element#elementId attribute which is a
 * read-only feature. Also supports derived and non changeable attributes.
 *
 * @author arichard
 */
public class EStringReadOnlyIfDescriptionProvider {
    private static final String IF_DESCRIPTION_ID = "EString";

    private static final String LABEL_DESCRIPTION_ID = "Label";

    private final ComposedAdapterFactory composedAdapterFactory;

    private final IPropertiesValidationProvider propertiesValidationProvider;

    private final Function<VariableManager, String> semanticTargetIdProvider;

    public EStringReadOnlyIfDescriptionProvider(ComposedAdapterFactory composedAdapterFactory, IPropertiesValidationProvider propertiesValidationProvider,
            Function<VariableManager, String> semanticTargetIdProvider) {
        this.composedAdapterFactory = Objects.requireNonNull(composedAdapterFactory);
        this.propertiesValidationProvider = Objects.requireNonNull(propertiesValidationProvider);
        this.semanticTargetIdProvider = Objects.requireNonNull(semanticTargetIdProvider);
    }

    public IfDescription getIfDescription() {
        return IfDescription.newIfDescription(IF_DESCRIPTION_ID)
                .targetObjectIdProvider(this.semanticTargetIdProvider)
                .predicate(this.getPredicate())
                .controlDescriptions(List.of(this.getLabelDescription()))
                .build();
    }

    private Function<VariableManager, Boolean> getPredicate() {
        return variableManager -> {
            var optionalEStructuralFeature = variableManager.get(PropertiesDefaultDescriptionProvider.ESTRUCTURAL_FEATURE, EAttribute.class);
            return optionalEStructuralFeature.filter(eAttribute -> {
                EClassifier eType = eAttribute.getEType();
                boolean readOnlyProperty = false;
                if (SysmlPackage.eINSTANCE.getElement_ElementId().equals(eAttribute)) {
                    readOnlyProperty = true;
                } else if (eAttribute.isDerived() || !eAttribute.isChangeable()) {
                    readOnlyProperty = true;
                }
                return readOnlyProperty && (!eAttribute.isMany() && (eType.equals(EcorePackage.Literals.ESTRING) || Objects.equals(eType.getInstanceClassName(), String.class.getName())));
            }).isPresent();
        };
    }

    private LabelDescription getLabelDescription() {
        return LabelDescription.newLabelDescription(LABEL_DESCRIPTION_ID)
                .idProvider(new WidgetIdProvider())
                .targetObjectIdProvider(this.semanticTargetIdProvider)
                .labelProvider(this.getLabelProvider())
                .valueProvider(this.getValueProvider())
                .diagnosticsProvider(this.propertiesValidationProvider.getDiagnosticsProvider())
                .kindProvider(this.propertiesValidationProvider.getKindProvider())
                .messageProvider(this.propertiesValidationProvider.getMessageProvider())
                .build();
    }

    private Function<VariableManager, String> getLabelProvider() {
        return new EStructuralFeatureLabelProvider(PropertiesDefaultDescriptionProvider.ESTRUCTURAL_FEATURE, this.composedAdapterFactory);
    }

    private Function<VariableManager, String> getValueProvider() {
        return variableManager -> {
            var optionalEObject = variableManager.get(VariableManager.SELF, EObject.class);
            var optionalEAttribute = variableManager.get(PropertiesDefaultDescriptionProvider.ESTRUCTURAL_FEATURE, EStructuralFeature.class);

            String value = "";
            if (optionalEObject.isPresent() && optionalEAttribute.isPresent()) {
                EObject eObject = optionalEObject.get();
                EStructuralFeature eSF = optionalEAttribute.get();

                Object eSFValue = eObject.eGet(eSF);
                if (eSFValue != null && !eSF.isMany()) {
                    if (eSFValue instanceof Enumerator enumerator) {
                        value = enumerator.getName();
                    } else if (eSFValue instanceof Element element) {
                        value = element.getName();
                    } else {
                        value = EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, eSFValue);
                    }
                }
            }

            return value;
        };
    }
}
