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
package org.eclipse.syson.diagram.general.view.edges;

import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.diagram.ArrowStyle;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.EdgeDescription;
import org.eclipse.sirius.components.view.diagram.EdgeStyle;
import org.eclipse.sirius.components.view.diagram.LineStyle;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.SysMLMetamodelHelper;
import org.eclipse.syson.diagram.general.view.nodes.AttributeDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.EnumerationDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.InterfaceDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.ItemDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PackageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PartDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PortDefinitionNodeDescriptionProvider;
import org.eclipse.syson.sysml.Subclassification;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Used to create the {@link Subclassification} edge description.
 *
 * @author arichard
 */
public class SubclassificationEdgeDescriptionProvider extends AbstractEdgeDescriptionProvider {

    public static final String NAME = "GV Edge Subclassification";

    public SubclassificationEdgeDescriptionProvider(IColorProvider colorProvider) {
        super(colorProvider);
    }

    @Override
    public EdgeDescription create() {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getSubclassification());
        return this.diagramBuilderHelper.newEdgeDescription()
                .domainType(domainType)
                .isDomainBasedEdge(true)
                .labelExpression("")
                .name(NAME)
                .palette(this.createEdgePalette())
                .semanticCandidatesExpression("aql:self.getAllReachable(" + domainType + ")")
                .sourceNodesExpression("aql:self." + SysmlPackage.eINSTANCE.getSubclassification_Subclassifier().getName())
                .style(this.createEdgeStyle())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .targetNodesExpression("aql:self." + SysmlPackage.eINSTANCE.getSubclassification_Superclassifier().getName())
                .build();
    }

    @Override
    public void link(DiagramDescription diagramDescription, IViewDiagramElementFinder cache) {
        var optEdgeDescription = cache.getEdgeDescription(NAME);
        var optAttributeDefinitionNodeDescription = cache.getNodeDescription(AttributeDefinitionNodeDescriptionProvider.NAME);
        var optEnumerationDefinitionNodeDescription = cache.getNodeDescription(EnumerationDefinitionNodeDescriptionProvider.NAME);
        var optInterfaceDefinitionNodeDescription = cache.getNodeDescription(InterfaceDefinitionNodeDescriptionProvider.NAME);
        var optItemDefinitionNodeDescription = cache.getNodeDescription(ItemDefinitionNodeDescriptionProvider.NAME);
        var optPackageNodeDescription = cache.getNodeDescription(PackageNodeDescriptionProvider.NAME);
        var optPartDefinitionNodeDescription = cache.getNodeDescription(PartDefinitionNodeDescriptionProvider.NAME);
        var optPortDefinitionNodeDescription = cache.getNodeDescription(PortDefinitionNodeDescriptionProvider.NAME);

        if (optEdgeDescription.isPresent()) {
            EdgeDescription edgeDescription = optEdgeDescription.get();
            diagramDescription.getEdgeDescriptions().add(edgeDescription);
            edgeDescription.getSourceNodeDescriptions().add(optAttributeDefinitionNodeDescription.get());
            edgeDescription.getTargetNodeDescriptions().add(optAttributeDefinitionNodeDescription.get());
            edgeDescription.getSourceNodeDescriptions().add(optEnumerationDefinitionNodeDescription.get());
            edgeDescription.getTargetNodeDescriptions().add(optEnumerationDefinitionNodeDescription.get());
            edgeDescription.getSourceNodeDescriptions().add(optInterfaceDefinitionNodeDescription.get());
            edgeDescription.getTargetNodeDescriptions().add(optInterfaceDefinitionNodeDescription.get());
            edgeDescription.getSourceNodeDescriptions().add(optItemDefinitionNodeDescription.get());
            edgeDescription.getTargetNodeDescriptions().add(optItemDefinitionNodeDescription.get());
            edgeDescription.getSourceNodeDescriptions().add(optPackageNodeDescription.get());
            edgeDescription.getTargetNodeDescriptions().add(optPackageNodeDescription.get());
            edgeDescription.getSourceNodeDescriptions().add(optPartDefinitionNodeDescription.get());
            edgeDescription.getTargetNodeDescriptions().add(optPartDefinitionNodeDescription.get());
            edgeDescription.getSourceNodeDescriptions().add(optPortDefinitionNodeDescription.get());
            edgeDescription.getTargetNodeDescriptions().add(optPortDefinitionNodeDescription.get());
        }
    }

    private EdgeStyle createEdgeStyle() {
        return this.diagramBuilderHelper.newEdgeStyle()
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_EDGE_COLOR))
                .edgeWidth(1)
                .lineStyle(LineStyle.SOLID)
                .sourceArrowStyle(ArrowStyle.NONE)
                .targetArrowStyle(ArrowStyle.INPUT_CLOSED_ARROW)
                .build();
    }
}
