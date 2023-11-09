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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.syson.sysml.AnnotatingElement;
import org.eclipse.syson.sysml.Annotation;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotating Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.impl.AnnotatingElementImpl#getAnnotatedElement <em>Annotated Element</em>}</li>
 *   <li>{@link org.eclipse.syson.sysml.impl.AnnotatingElementImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnnotatingElementImpl extends ElementImpl implements AnnotatingElement {
    /**
     * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAnnotation()
     * @generated
     * @ordered
     */
    protected EList<Annotation> annotation;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AnnotatingElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SysmlPackage.eINSTANCE.getAnnotatingElement();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public EList<Element> getAnnotatedElement() {
        // TODO: implement this method to return the 'Annotated Element' reference list
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
    public EList<Annotation> getAnnotation() {
        if (annotation == null) {
            annotation = new EObjectWithInverseResolvingEList<Annotation>(Annotation.class, this, SysmlPackage.ANNOTATING_ELEMENT__ANNOTATION, SysmlPackage.ANNOTATION__ANNOTATING_ELEMENT);
        }
        return annotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATION:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotation()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATION:
                return ((InternalEList<?>)getAnnotation()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATED_ELEMENT:
                return getAnnotatedElement();
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATION:
                return getAnnotation();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATION:
                getAnnotation().clear();
                getAnnotation().addAll((Collection<? extends Annotation>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATION:
                getAnnotation().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATED_ELEMENT:
                return !getAnnotatedElement().isEmpty();
            case SysmlPackage.ANNOTATING_ELEMENT__ANNOTATION:
                return annotation != null && !annotation.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //AnnotatingElementImpl
