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

import org.eclipse.syson.sysml.AttributeDefinition;
import org.eclipse.syson.sysml.AttributeUsage;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.EnumerationDefinition;
import org.eclipse.syson.sysml.InterfaceDefinition;
import org.eclipse.syson.sysml.InterfaceUsage;
import org.eclipse.syson.sysml.ItemDefinition;
import org.eclipse.syson.sysml.ItemUsage;
import org.eclipse.syson.sysml.Package;
import org.eclipse.syson.sysml.PartDefinition;
import org.eclipse.syson.sysml.PartUsage;
import org.eclipse.syson.sysml.PortDefinition;
import org.eclipse.syson.sysml.PortUsage;
import org.eclipse.syson.sysml.util.SysmlSwitch;

/**
 * Switch returning the label of a container node in the General View.
 *
 * @author arichard
 */
public class ContainerLabelSwitch extends SysmlSwitch<String> {

    private static final String CR = "\n";

    private static final String CLOSE_QUOTE = "\u00BB";

    private static final String OPEN_QUOTE = "\u00AB";

    @Override
    public String caseElement(Element object) {
        String declaredName = object.getDeclaredName();
        if (declaredName == null) {
            return "";
        }
        return declaredName;
    }

    @Override
    public String caseAttributeDefinition(AttributeDefinition object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("attribute def")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String caseAttributeUsage(AttributeUsage object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("attribute")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String caseEnumerationDefinition(EnumerationDefinition object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("enumeration def")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String caseInterfaceDefinition(InterfaceDefinition object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("interface def")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String caseInterfaceUsage(InterfaceUsage object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("interface")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String caseItemDefinition(ItemDefinition object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("item def")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String caseItemUsage(ItemUsage object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("item")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String casePartDefinition(PartDefinition object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("part def")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String casePackage(Package object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("package")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String casePartUsage(PartUsage object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("part")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String casePortDefinition(PortDefinition object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("port def")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }

    @Override
    public String casePortUsage(PortUsage object) {
        StringBuilder label = new StringBuilder();
        label
            .append(OPEN_QUOTE)
            .append("port")
            .append(CLOSE_QUOTE)
            .append(CR)
            .append(this.caseElement(object));
        return label.toString();
    }
}
