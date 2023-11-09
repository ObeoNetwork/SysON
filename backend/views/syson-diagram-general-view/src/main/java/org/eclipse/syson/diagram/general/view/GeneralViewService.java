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
package org.eclipse.syson.diagram.general.view;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.syson.diagram.general.view.services.ContainerLabelSwitch;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.FeatureMembership;
import org.eclipse.syson.sysml.Membership;
import org.eclipse.syson.sysml.PartUsage;
import org.eclipse.syson.sysml.SysmlFactory;

/**
 * List of all Java services used by the {@link GeneralViewDiagramDescriptionProvider}.
 *
 * @author arichard
 */
public class GeneralViewService {

    /**
     * Return the container label for the given {@link Element}.
     *
     * @param element
     *            the given {@link Element}.
     * @return the container label for the given {@link Element}.
     */
    public String getContainerLabel(Element element) {
        return new ContainerLabelSwitch().doSwitch(element);
    }

    /**
     * Delete the given {@link Element} and its container if it's a {@link Membership}.
     *
     * @param element
     *            the {@link Element} to delete.
     * @return the deleted element.
     */
    public EObject deleteWithMembership(Element element) {
        Element elementToDelete = element;
        if (element.eContainer() instanceof Membership membership) {
            elementToDelete = membership;
        }
        EcoreUtil.remove(elementToDelete);
        return element;
    }

    /**
     * Create a {@link PartUsage} under the given {@link PartUsage}.
     *
     * @param partUsage
     *            the {@link PartUsage} on which we want to create a nested {@link PartUsage}.
     * @return the created element.
     */
    public PartUsage createNestedPartUsage(PartUsage partUsage) {
        PartUsage newPartUsage = SysmlFactory.eINSTANCE.createPartUsage();
        newPartUsage.setDeclaredName("part");
        FeatureMembership membership = SysmlFactory.eINSTANCE.createFeatureMembership();
        membership.getOwnedRelatedElement().add(newPartUsage);
        partUsage.getOwnedRelationship().add(membership);
        return newPartUsage;
    }

    /**
     * Get all reachable elements of a type in the {@link ResourceSet} of given {@link EObject}.
     *
     * @param eObject
     *            the {@link EObject} stored in a {@link ResourceSet}
     * @param type
     *            the search typed (either simple or qualified named of the EClass ("Package" vs "sysml::Package")
     * @return a list of reachable object
     */
    public List<EObject> getAllReachable(EObject eObject, String type) {
        return this.getAllReachable(eObject, type, true);
    }

    /**
     * Get all reachable elements of a type in the {@link ResourceSet} of the given {@link EObject}.
     *
     * @param eObject
     *            the {@link EObject} stored in a {@link ResourceSet}
     * @param type
     *            the searched type (either simple or qualified named of the EClass ("Package" vs "sysml::Package")
     * @param withSubType
     *            <code>true</code> to include any element with a compatible type, <code>false</code> otherwise
     * @return a list of reachable object
     */
    public List<EObject> getAllReachable(EObject eObject, String type, boolean withSubType) {
        EClass eClass = SysMLMetamodelHelper.toEClass(type);
        return this.getAllReachable(eObject, eClass, withSubType);
    }

    /**
     * Get all reachable elements of the type given by the {@link EClass} in the {@link ResourceSet} of the given {@link EObject}.
     *
     * @param eObject
     *            the {@link EObject} stored in a {@link ResourceSet}
     * @param eClass
     *            the searched {@link EClass}
     * @return a list of reachable object
     */
    public List<EObject> getAllReachable(EObject eObject, EClass eClass) {
        return this.getAllReachable(eObject, eClass, true);
    }

    /**
     * Get all reachable elements of the type given by the {@link EClass} in the {@link ResourceSet} of the given {@link EObject}.
     *
     * @param eObject
     *            the {@link EObject} stored in a {@link ResourceSet}
     * @param eClass
     *            the searched {@link EClass}
     * @param withSubType
     *            <code>true</code> to include any element with a compatible type, <code>false</code> otherwise
     * @return a list of reachable object
     */
    public List<EObject> getAllReachable(EObject eObject, EClass eClass, boolean withSubType) {
        ResourceSet rs = eObject.eResource().getResourceSet();
        if (rs != null && eClass != null) {
            final Predicate<Notifier> predicate;
            if (withSubType) {
                predicate = e -> e instanceof EObject && eClass.isInstance(e);
            } else {
                predicate = e -> e instanceof EObject && eClass == ((EObject) e).eClass();
            }

            return this.getSysMLv2Resources(rs.getResources())
                    .flatMap(r -> this.eAllContentsStreamWithSelf(r))
                    .filter(predicate)
                    .map(EObject.class::cast)
                    .toList();
        } else {
            return List.of();
        }
    }

    private Stream<Resource> getSysMLv2Resources(EList<Resource> resources) {
        return resources.stream().filter(r -> !r.getContents().isEmpty() && r.getContents().get(0) instanceof Element);
    }

    private Stream<Notifier> eAllContentsStreamWithSelf(Resource r) {
        if (r == null) {
            return Stream.empty();
        }
        return Stream.concat(Stream.of(r), StreamSupport.stream(Spliterators.spliteratorUnknownSize(r.getAllContents(), Spliterator.NONNULL), false));
    }
}
