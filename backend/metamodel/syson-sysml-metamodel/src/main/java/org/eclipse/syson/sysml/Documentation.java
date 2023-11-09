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
package org.eclipse.syson.sysml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.syson.sysml.Documentation#getDocumentedElement <em>Documented Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.syson.sysml.SysmlPackage#getDocumentation()
 * @model
 * @generated
 */
public interface Documentation extends Comment {
    /**
     * Returns the value of the '<em><b>Documented Element</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.syson.sysml.Element#getDocumentation <em>Documentation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documented Element</em>' reference.
     * @see org.eclipse.syson.sysml.SysmlPackage#getDocumentation_DocumentedElement()
     * @see org.eclipse.syson.sysml.Element#getDocumentation
     * @model opposite="documentation" required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
     * @generated
     */
    Element getDocumentedElement();

} // Documentation
