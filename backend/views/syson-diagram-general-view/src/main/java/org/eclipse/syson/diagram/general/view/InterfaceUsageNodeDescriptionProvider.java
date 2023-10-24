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
import org.eclipse.sirius.components.view.builder.generated.ListLayoutStrategyDescriptionBuilder;
import org.eclipse.sirius.components.view.builder.providers.IColorProvider;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.SynchronizationPolicy;
import org.eclipse.syson.sysml.SysmlPackage;

/**
 * Used to create the interface usage node description.
 * 
 * @author arichard
 */
public class InterfaceUsageNodeDescriptionProvider extends AbstractNodeDescriptionProvider {

    public static final String NAME = "GV Node InterfaceUsage";

    public InterfaceUsageNodeDescriptionProvider(IColorProvider colorProvider) {
        super(colorProvider);
    }

    @Override
    public NodeDescription create() {
        String domainType = SysMLMetamodelHelper.buildQualifiedName(SysmlPackage.eINSTANCE.getInterfaceUsage());
        return this.diagramBuilderHelper.newNodeDescription()
            .childrenDescriptions(this.createUsageAttributesCompartment(NAME), this.createUsagePortsCompartment(NAME))
            .childrenLayoutStrategy(new ListLayoutStrategyDescriptionBuilder().build())
            .domainType(domainType)
            .labelExpression("aql:self.getContainerLabel()")
            .name(NAME)
            .semanticCandidatesExpression("aql:self.getAllReachable(" + domainType + ")")
            .style(this.createUsageNodeStyle())
            .userResizable(true)
            .palette(this.createDefaultNodePalette())
            .synchronizationPolicy(SynchronizationPolicy.UNSYNCHRONIZED)
            .build();
    }

    @Override
    public void link(DiagramDescription diagramDescription, IViewDiagramElementFinder cache) {
        diagramDescription.getNodeDescriptions().add(cache.getNodeDescription(NAME).get());
    }
}