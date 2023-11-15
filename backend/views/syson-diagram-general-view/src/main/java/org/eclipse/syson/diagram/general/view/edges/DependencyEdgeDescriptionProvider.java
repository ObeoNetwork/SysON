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

import java.util.Objects;

import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.generated.ChangeContextBuilder;
import org.eclipse.sirius.components.view.builder.generated.DeleteToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.DiagramBuilders;
import org.eclipse.sirius.components.view.builder.generated.ViewBuilders;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.builder.providers.IEdgeDescriptionProvider;
import org.eclipse.sirius.components.view.diagram.ArrowStyle;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.EdgeDescription;
import org.eclipse.sirius.components.view.diagram.EdgePalette;
import org.eclipse.sirius.components.view.diagram.EdgeStyle;
import org.eclipse.sirius.components.view.diagram.LineStyle;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.diagram.general.view.GeneralViewDiagramDescriptionProvider;
import org.eclipse.syson.diagram.general.view.SysMLMetamodelHelper;
import org.eclipse.syson.diagram.general.view.nodes.AttributeDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.AttributeUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.EnumerationDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.InterfaceDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.InterfaceUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.ItemDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.ItemUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PackageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PartDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PartUsageNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PortDefinitionNodeDescriptionProvider;
import org.eclipse.syson.diagram.general.view.nodes.PortUsageNodeDescriptionProvider;
import org.eclipse.syson.sysml.Dependency;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Used to create the {@link Dependency} edge description.
 *
 * @author arichard
 */
public class DependencyEdgeDescriptionProvider implements IEdgeDescriptionProvider {

    public static final String NAME = "GV Edge Dependency";

    private final ViewBuilders viewBuilderHelper = new ViewBuilders();

    private final DiagramBuilders diagramBuilderHelper = new DiagramBuilders();

    private IColorProvider colorProvider;

    public DependencyEdgeDescriptionProvider(IColorProvider colorProvider) {
        this.colorProvider = Objects.requireNonNull(colorProvider);
    }

    @Override
    public EdgeDescription create() {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getDependency());
        return this.diagramBuilderHelper.newEdgeDescription()
                .domainType(domainType)
                .isDomainBasedEdge(true)
                .labelExpression("aql:self.getDependencyEdgeLabel()")
                .name(NAME)
                .palette(this.createEdgePalette())
                .semanticCandidatesExpression("aql:self.getAllReachable(" + domainType + ")")
                .sourceNodesExpression("aql:self.client")
                .style(this.createEdgeStyle())
                .synchronizationPolicy(SynchronizationPolicy.SYNCHRONIZED)
                .targetNodesExpression("aql:self.supplier")
                .build();
    }

    @Override
    public void link(DiagramDescription diagramDescription, IViewDiagramElementFinder cache) {
        var optEdgeDescription = cache.getEdgeDescription(NAME);
        var optAttributeDefinitionNodeDescription = cache.getNodeDescription(AttributeDefinitionNodeDescriptionProvider.NAME);
        var optAttributeUsageNodeDescription = cache.getNodeDescription(AttributeUsageNodeDescriptionProvider.NAME);
        var optEnumerationDefinitionNodeDescription = cache.getNodeDescription(EnumerationDefinitionNodeDescriptionProvider.NAME);
        var optInterfaceDefinitionNodeDescription = cache.getNodeDescription(InterfaceDefinitionNodeDescriptionProvider.NAME);
        var optInterfaceUsageNodeDescription = cache.getNodeDescription(InterfaceUsageNodeDescriptionProvider.NAME);
        var optItemDefinitionNodeDescription = cache.getNodeDescription(ItemDefinitionNodeDescriptionProvider.NAME);
        var optItemUsageNodeDescription = cache.getNodeDescription(ItemUsageNodeDescriptionProvider.NAME);
        var optPackageNodeDescription = cache.getNodeDescription(PackageNodeDescriptionProvider.NAME);
        var optPartDefinitionNodeDescription = cache.getNodeDescription(PartDefinitionNodeDescriptionProvider.NAME);
        var optPartUsageNodeDescription = cache.getNodeDescription(PartUsageNodeDescriptionProvider.NAME);
        var optPortDefinitionNodeDescription = cache.getNodeDescription(PortDefinitionNodeDescriptionProvider.NAME);
        var optPortUsageNodeDescription = cache.getNodeDescription(PortUsageNodeDescriptionProvider.NAME);

        if (optEdgeDescription.isPresent()) {
            EdgeDescription edgeDescription = optEdgeDescription.get();
            diagramDescription.getEdgeDescriptions().add(edgeDescription);
            if (optAttributeDefinitionNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optAttributeDefinitionNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optAttributeDefinitionNodeDescription.get());
            }
            if (optAttributeUsageNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optAttributeUsageNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optAttributeUsageNodeDescription.get());
            }
            if (optEnumerationDefinitionNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optEnumerationDefinitionNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optEnumerationDefinitionNodeDescription.get());
            }
            if (optInterfaceDefinitionNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optInterfaceDefinitionNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optInterfaceDefinitionNodeDescription.get());
            }
            if (optInterfaceUsageNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optInterfaceUsageNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optInterfaceUsageNodeDescription.get());
            }
            if (optItemDefinitionNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optItemDefinitionNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optItemDefinitionNodeDescription.get());
            }
            if (optItemUsageNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optItemUsageNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optItemUsageNodeDescription.get());
            }
            if (optPackageNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optPackageNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optPackageNodeDescription.get());
            }
            if (optPartDefinitionNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optPartDefinitionNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optPartDefinitionNodeDescription.get());
            }
            if (optPartUsageNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optPartUsageNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optPartUsageNodeDescription.get());
            }
            if (optPortDefinitionNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optPortDefinitionNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optPortDefinitionNodeDescription.get());
            }
            if (optPortUsageNodeDescription.isPresent()) {
                edgeDescription.getSourceNodeDescriptions().add(optPortUsageNodeDescription.get());
                edgeDescription.getTargetNodeDescriptions().add(optPortUsageNodeDescription.get());
            }
        }
    }

    private EdgeStyle createEdgeStyle() {
        return this.diagramBuilderHelper.newEdgeStyle()
                .color(this.colorProvider.getColor(GeneralViewDiagramDescriptionProvider.DEFAULT_EDGE_COLOR))
                .edgeWidth(1)
                .lineStyle(LineStyle.DASH)
                .sourceArrowStyle(ArrowStyle.NONE)
                .targetArrowStyle(ArrowStyle.INPUT_ARROW)
                .build();
    }

    protected EdgePalette createEdgePalette() {
        ChangeContextBuilder changeContext = this.viewBuilderHelper.newChangeContext();
        changeContext.expression("aql:self.deleteFromModel()");

        DeleteToolBuilder deleteTool = this.diagramBuilderHelper.newDeleteTool().name("Delete from Model").body(changeContext.build());

        return this.diagramBuilderHelper.newEdgePalette().deleteTool(deleteTool.build()).build();
    }
}
