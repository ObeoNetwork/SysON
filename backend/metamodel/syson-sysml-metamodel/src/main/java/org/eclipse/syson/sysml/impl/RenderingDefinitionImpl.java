/**
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
 */
package org.eclipse.syson.sysml.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.syson.sysml.RenderingDefinition;
import org.eclipse.syson.sysml.RenderingUsage;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rendering Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.RenderingDefinitionImpl#getRendering <em>Rendering</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RenderingDefinitionImpl extends PartDefinitionImpl implements RenderingDefinition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RenderingDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getRenderingDefinition();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<RenderingUsage> getRendering() {
        // TODO: implement this method to return the 'Rendering' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SysmlPackage.RENDERING_DEFINITION__RENDERING:
                return getRendering();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SysmlPackage.RENDERING_DEFINITION__RENDERING:
                return !getRendering().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //RenderingDefinitionImpl
