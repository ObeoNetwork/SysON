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
package org.eclipse.syson.diagram.general.view.nodes;

import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodeStyleDescription;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.SysMLMetamodelHelper;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Used to create the enumeration definition node description.
 * 
 * @author arichard
 */
public class EnumerationDefinitionNodeDescriptionProvider extends AbstractNodeDescriptionProvider {

    public static final String NAME = "GV Node EnumerationDefinition";

    public EnumerationDefinitionNodeDescriptionProvider(IColorProvider colorProvider) {
        super(colorProvider);
    }

    @Override
    public NodeDescription create() {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getEnumerationDefinition());
        return this.diagramBuilderHelper.newNodeDescription()
                .defaultHeightExpression("60")
                .defaultWidthExpression(GeneralViewDiagramDescriptionProvider.DEFAULT_NODE_WIDTH)
                .domainType(domainType)
                .labelExpression("aql:self.getContainerLabel()")
                .name(NAME)
                .semanticCandidatesExpression("aql:self.getAllReachable(" + domainType + ")")
                .style(this.createDefinitionNodeStyle())
                .userResizable(true)
                .palette(this.createDefaultNodePalette())
                .synchronizationPolicy(SynchronizationPolicy.UNSYNCHRONIZED)
                .build();
    }

    @Override
    public void link(DiagramDescription diagramDescription, IViewDiagramElementFinder cache) {
        diagramDescription.getNodeDescriptions().add(cache.getNodeDescription(NAME).get());
    }

    @Override
    protected NodeStyleDescription createDefinitionNodeStyle() {
        return this.diagramBuilderHelper.newRectangularNodeStyleDescription()
                .borderColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BORDER_COLOR))
                .borderRadius(0)
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_BACKGROUND_COLOR))
                .displayHeaderSeparator(false)
                .labelColor(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_LABEL_COLOR))
                .showIcon(true)
                .withHeader(false)
                .build();
    }
}
