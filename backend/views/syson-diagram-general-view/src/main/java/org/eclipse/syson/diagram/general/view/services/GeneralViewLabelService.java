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
package org.eclipse.syson.diagram.general.view.services;

import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.LabelConstants;
import org.eclipse.syson.sysml.Dependency;
import org.eclipse.syson.sysml.Element;

/**
 * Label-related Java services used by the {@link GeneralViewDiagramDescriptionProvider}.
 *
 * @author arichard
 */
public class GeneralViewLabelService {

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
     * Return the edge label for the given {@link Dependency}.
     *
     * @param dependency
     *            the given {@link Dependency}.
     * @return the edge label for the given {@link Dependency}.
     */
    public String getDependencyEdgeLabel(Dependency dependency) {
        return LabelConstants.OPEN_QUOTE + "dependency" + LabelConstants.CLOSE_QUOTE + LabelConstants.CR + dependency.getDeclaredName();
    }

}
