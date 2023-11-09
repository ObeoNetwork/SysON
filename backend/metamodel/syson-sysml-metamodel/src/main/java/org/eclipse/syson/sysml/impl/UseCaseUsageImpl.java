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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.sysml.UseCaseDefinition;
import org.eclipse.syson.sysml.UseCaseUsage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.UseCaseUsageImpl#getIncludedUseCase <em>Included Use Case</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.UseCaseUsageImpl#getUseCaseDefinition <em>Use Case Definition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UseCaseUsageImpl extends CaseUsageImpl implements UseCaseUsage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UseCaseUsageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getUseCaseUsage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<UseCaseUsage> getIncludedUseCase() {
        // TODO: implement this method to return the 'Included Use Case' reference list
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
    public UseCaseDefinition getUseCaseDefinition() {
        UseCaseDefinition useCaseDefinition = basicGetUseCaseDefinition();
        return useCaseDefinition != null && useCaseDefinition.eIsProxy() ? (UseCaseDefinition)eResolveProxy((InternalEObject)useCaseDefinition) : useCaseDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseCaseDefinition basicGetUseCaseDefinition() {
        // TODO: implement this method to return the 'Use Case Definition' reference
        // -> do not perform proxy resolution
        // Ensure that you remove @generated or mark it @generated NOT
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
            case SysmlPackage.USE_CASE_USAGE__INCLUDED_USE_CASE:
                return getIncludedUseCase();
            case SysmlPackage.USE_CASE_USAGE__USE_CASE_DEFINITION:
                if (resolve) return getUseCaseDefinition();
                return basicGetUseCaseDefinition();
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
            case SysmlPackage.USE_CASE_USAGE__INCLUDED_USE_CASE:
                return !getIncludedUseCase().isEmpty();
            case SysmlPackage.USE_CASE_USAGE__USE_CASE_DEFINITION:
                return basicGetUseCaseDefinition() != null;
        }
        return super.eIsSet(featureID);
    }

} //UseCaseUsageImpl
