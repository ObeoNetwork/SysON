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

import org.eclipse.sirius.components.view.builder.IViewDiagramElementFinder;
import org.eclipse.sirius.components.view.builder.generated.ChangeContextBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateInstanceBuilder;
import org.eclipse.sirius.components.view.builder.generated.CreateViewBuilder;
import org.eclipse.sirius.components.view.builder.generated.DeleteToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.ListLayoutStrategyDescriptionBuilder;
import org.eclipse.sirius.components.view.builder.generated.NodeToolBuilder;
import org.eclipse.sirius.components.view.builder.generated.SetValueBuilder;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.NodeContainmentKind;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodePalette;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Used to create the part usage node description.
 *
 * @author arichard
 */
public class PartUsageNodeDescriptionProvider extends AbstractNodeDescriptionProvider {

    public static final String NAME = "GV Node PartUsage";


    public PartUsageNodeDescriptionProvider(IColorProvider colorProvider) {
        super(colorProvider);
    }

    @Override
    public NodeDescription create() {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage());
        return this.diagramBuilderHelper.newNodeDescription()
            .childrenDescriptions(this.createUsageAttributesCompartment(NAME), this.createUsagePortsCompartment(NAME))
            .childrenLayoutStrategy(new ListLayoutStrategyDescriptionBuilder().build())
            .domainType(domainType)
            .labelExpression("aql:self.getContainerLabel()")
            .name(NAME)
            .semanticCandidatesExpression("aql:self.getAllReachable(" + domainType + ")")
            .style(this.createUsageNodeStyle())
            .userResizable(true)
            .synchronizationPolicy(SynchronizationPolicy.UNSYNCHRONIZED)
            .build();
    }

    @Override
    public void link(DiagramDescription diagramDescription, IViewDiagramElementFinder cache) {
        NodeDescription partUsageNodeDescription = cache.getNodeDescription(NAME).get();
        diagramDescription.getNodeDescriptions().add(partUsageNodeDescription);
        partUsageNodeDescription.setPalette(this.createPartUsagePalette(partUsageNodeDescription));
    }

    protected NodePalette createPartUsagePalette(NodeDescription nodeDescription) {
        SetValueBuilder setValue = this.viewBuilderHelper.newSetValue();
        setValue
                .featureName("declaredName")
                .valueExpression("PartUsage");

        ChangeContextBuilder changeContextNewInstance = this.viewBuilderHelper.newChangeContext();
        changeContextNewInstance
                .expression("aql:newInstance")
                .children(setValue.build());

        CreateInstanceBuilder createEClassInstance =  this.viewBuilderHelper.newCreateInstance();
        createEClassInstance
                .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getPartUsage()))
                .referenceName("ownedRelatedElement")
                .variableName("newInstance")
                .children(changeContextNewInstance.build());

        CreateViewBuilder createView = this.diagramBuilderHelper.newCreateView();
        createView
                .containmentKind(NodeContainmentKind.CHILD_NODE)
                .elementDescription(nodeDescription)
                .parentViewExpression("aql:diagram")
                .semanticElementExpression("aql:newInstance")
                .variableName("newInstanceView");

        ChangeContextBuilder changeContexMembership = this.viewBuilderHelper.newChangeContext();
        changeContexMembership
                .expression("aql:newFeatureMembership")
                .children(createEClassInstance.build(), createView.build());

        CreateInstanceBuilder createMembership =  this.viewBuilderHelper.newCreateInstance();
        createMembership
                .typeName(SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getFeatureMembership()))
                .referenceName("ownedRelationship")
                .variableName("newFeatureMembership")
                .children(changeContexMembership.build());

        NodeToolBuilder nodeTool = this.diagramBuilderHelper.newNodeTool();
        nodeTool
                .name("PartUsage")
                .body(createMembership.build())
                .build();

        ChangeContextBuilder changeContext = this.viewBuilderHelper.newChangeContext();
        changeContext
                .expression("aql:self.deleteWithMembership()");

        DeleteToolBuilder deleteTool = this.diagramBuilderHelper.newDeleteTool()
                .name("Delete from Model")
                .body(changeContext.build());

        return this.diagramBuilderHelper.newNodePalette()
                .deleteTool(deleteTool.build())
                .nodeTools(nodeTool.build())
                .build();
    }
}
