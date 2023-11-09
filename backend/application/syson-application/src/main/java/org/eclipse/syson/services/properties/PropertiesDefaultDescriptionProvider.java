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
package org.eclipse.syson.services.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.sirius.components.collaborative.forms.PropertiesEventProcessorFactory;
import org.eclipse.sirius.components.collaborative.forms.api.IPropertiesDefaultDescriptionProvider;
import org.eclipse.sirius.components.compatibility.emf.properties.EBooleanIfDescriptionProvider;
import org.eclipse.sirius.components.compatibility.emf.properties.EEnumIfDescriptionProvider;
import org.eclipse.sirius.components.compatibility.emf.properties.NumberIfDescriptionProvider;
import org.eclipse.sirius.components.compatibility.emf.properties.api.IPropertiesValidationProvider;
import org.eclipse.sirius.components.core.api.IFeedbackMessageService;
import org.eclipse.sirius.components.core.api.IObjectService;
import org.eclipse.sirius.components.emf.services.api.IEMFKindService;
import org.eclipse.sirius.components.emf.services.messages.IEMFMessageService;
import org.eclipse.sirius.components.forms.description.AbstractControlDescription;
import org.eclipse.sirius.components.forms.description.ForDescription;
import org.eclipse.sirius.components.forms.description.FormDescription;
import org.eclipse.sirius.components.forms.description.GroupDescription;
import org.eclipse.sirius.components.forms.description.PageDescription;
import org.eclipse.sirius.components.representations.GetOrCreateRandomIdProvider;
import org.eclipse.sirius.components.representations.VariableManager;
import org.eclipse.syson.sysml.Element;
import org.eclipse.syson.sysml.Feature;
import org.eclipse.syson.sysml.FeatureTyping;
import org.eclipse.syson.sysml.Subsetting;
import org.eclipse.syson.sysml.SysmlPackage;
import org.eclipse.syson.sysml.Usage;
import org.springframework.stereotype.Service;

/**
 * Used to provide the SysMLv2 Properties descriptions.
 *
 * @author arichard
 */
@Service
public class PropertiesDefaultDescriptionProvider implements IPropertiesDefaultDescriptionProvider {

    public static final String ESTRUCTURAL_FEATURE = "eStructuralFeature";

    private final IObjectService objectService;

    private final IEMFKindService emfKindService;

    private final IFeedbackMessageService feedbackMessageService;

    private final ComposedAdapterFactory composedAdapterFactory;

    private final IPropertiesValidationProvider propertiesValidationProvider;

    private final IEMFMessageService emfMessageService;

    private final Function<VariableManager, String> semanticTargetIdProvider;

    public PropertiesDefaultDescriptionProvider(IObjectService objectService, IEMFKindService emfKindService, IFeedbackMessageService feedbackMessageService,
            ComposedAdapterFactory composedAdapterFactory, IPropertiesValidationProvider propertiesValidationProvider,
            IEMFMessageService emfMessageService) {
        this.objectService = Objects.requireNonNull(objectService);
        this.emfKindService = Objects.requireNonNull(emfKindService);
        this.feedbackMessageService = Objects.requireNonNull(feedbackMessageService);
        this.composedAdapterFactory = Objects.requireNonNull(composedAdapterFactory);
        this.propertiesValidationProvider = Objects.requireNonNull(propertiesValidationProvider);
        this.emfMessageService = Objects.requireNonNull(emfMessageService);
        this.semanticTargetIdProvider = variableManager -> variableManager.get(VariableManager.SELF, Object.class).map(objectService::getId).orElse(null);
    }

    @Override
    public FormDescription getFormDescription() {
        List<GroupDescription> groupDescriptions = new ArrayList<>();
        GroupDescription corePropertiesGroupDescription = this.getCorePropertiesGroupDescription();
        groupDescriptions.add(corePropertiesGroupDescription);
        GroupDescription subsettingPropertiesGroupDescription = this.getSubsettingPropertiesGroupDescription();
        groupDescriptions.add(subsettingPropertiesGroupDescription);
        GroupDescription redefinitionPropertiesGroupDescription = this.getRedefinitionPropertiesGroupDescription();
        groupDescriptions.add(redefinitionPropertiesGroupDescription);
        GroupDescription featureTypingPropertiesGroupDescription = this.getFeatureTypingPropertiesGroupDescription();
        groupDescriptions.add(featureTypingPropertiesGroupDescription);

        List<PageDescription> pageDescriptions = new ArrayList<>();
        PageDescription firstPageDescription = this.getPageDescription(groupDescriptions);
        pageDescriptions.add(firstPageDescription);

        Function<VariableManager, String> labelProvider = variableManager -> "Properties";

        Function<VariableManager, String> targetObjectIdProvider = variableManager -> variableManager.get(VariableManager.SELF, Object.class)
                .map(this.objectService::getId)
                .orElse(null);

        return FormDescription.newFormDescription(PropertiesEventProcessorFactory.DETAILS_VIEW_ID)
                .label("Default form description")
                .idProvider(new GetOrCreateRandomIdProvider())
                .labelProvider(labelProvider)
                .targetObjectIdProvider(targetObjectIdProvider)
                .canCreatePredicate(variableManager -> false)
                .pageDescriptions(pageDescriptions)
                .build();
    }

    private PageDescription getPageDescription(List<GroupDescription> groupDescriptions) {
        Function<VariableManager, String> idProvider = variableManager -> {
            var optionalSelf = variableManager.get(VariableManager.SELF, Object.class);
            if (optionalSelf.isPresent()) {
                Object self = optionalSelf.get();
                return this.objectService.getId(self);
            }
            return UUID.randomUUID().toString();
        };

        Function<VariableManager, String> labelProvider = variableManager -> {
            var optionalSelf = variableManager.get(VariableManager.SELF, Object.class);
            if (optionalSelf.isPresent()) {
                Object self = optionalSelf.get();
                return this.objectService.getLabel(self);
            }
            return UUID.randomUUID().toString();
        };

        return PageDescription.newPageDescription("firstPageId")
                .idProvider(idProvider)
                .labelProvider(labelProvider)
                .semanticElementsProvider(variableManager -> Collections.singletonList(variableManager.getVariables().get(VariableManager.SELF)))
                .groupDescriptions(groupDescriptions)
                .canCreatePredicate(variableManager -> true)
                .build();
    }

    private GroupDescription getGroupDescription(String groupId, String groupLabel, Function<VariableManager, List<?>> semanticElementsProvider) {
        List<AbstractControlDescription> controlDescriptions = new ArrayList<>();

        Function<VariableManager, List<?>> iterableProvider = variableManager -> {
            List<Object> objects = new ArrayList<>();

            Object self = variableManager.getVariables().get(VariableManager.SELF);
            if (self instanceof EObject) {
                EObject eObject = (EObject) self;

                List<IItemPropertyDescriptor> propertyDescriptors = Optional.ofNullable(this.composedAdapterFactory.adapt(eObject, IItemPropertySource.class))
                        .filter(IItemPropertySource.class::isInstance)
                        .map(IItemPropertySource.class::cast)
                        .map(iItemPropertySource -> iItemPropertySource.getPropertyDescriptors(eObject))
                        .orElse(new ArrayList<>());

                propertyDescriptors.stream()
                        .map(propertyDescriptor -> propertyDescriptor.getFeature(eObject))
                        .filter(EStructuralFeature.class::isInstance)
                        .map(EStructuralFeature.class::cast)
                        .forEach(objects::add);
            }
            return objects;
        };

        List<AbstractControlDescription> ifDescriptions = new ArrayList<>();
        ifDescriptions.add(new EStringReadOnlyIfDescriptionProvider(this.composedAdapterFactory, this.propertiesValidationProvider, this.semanticTargetIdProvider).getIfDescription());
        ifDescriptions.add(new EStringIfDescriptionProvider(this.composedAdapterFactory, this.propertiesValidationProvider, this.semanticTargetIdProvider).getIfDescription());
        ifDescriptions.add(new EBooleanIfDescriptionProvider(this.composedAdapterFactory, this.propertiesValidationProvider, this.semanticTargetIdProvider).getIfDescription());
        ifDescriptions.add(new EEnumIfDescriptionProvider(this.composedAdapterFactory, this.propertiesValidationProvider, this.semanticTargetIdProvider).getIfDescription());

        ifDescriptions.add(new NonContainmentReferenceIfDescriptionProvider(this.composedAdapterFactory, this.objectService, this.emfKindService, this.feedbackMessageService,
                this.propertiesValidationProvider, this.semanticTargetIdProvider).getIfDescription());

        var numericDataTypes = List.of(
                EcorePackage.Literals.EINT,
                EcorePackage.Literals.EINTEGER_OBJECT,
                EcorePackage.Literals.EDOUBLE,
                EcorePackage.Literals.EDOUBLE_OBJECT,
                EcorePackage.Literals.EFLOAT,
                EcorePackage.Literals.EFLOAT_OBJECT,
                EcorePackage.Literals.ELONG,
                EcorePackage.Literals.ELONG_OBJECT,
                EcorePackage.Literals.ESHORT,
                EcorePackage.Literals.ESHORT_OBJECT
                );

        for (var dataType : numericDataTypes) {
            ifDescriptions.add(new NumberIfDescriptionProvider(dataType, this.composedAdapterFactory, this.propertiesValidationProvider, this.emfMessageService, this.semanticTargetIdProvider)
                    .getIfDescription());
        }

        ForDescription forDescription = ForDescription.newForDescription("forId")
                .targetObjectIdProvider(this.semanticTargetIdProvider)
                .iterator(ESTRUCTURAL_FEATURE)
                .iterableProvider(iterableProvider)
                .controlDescriptions(ifDescriptions)
                .build();

        controlDescriptions.add(forDescription);

        return GroupDescription.newGroupDescription(groupId)
                .idProvider(variableManager -> groupLabel)
                .labelProvider(variableManager -> groupLabel)
                .semanticElementsProvider(semanticElementsProvider)
                .controlDescriptions(controlDescriptions)
                .build();
    }

    private GroupDescription getCorePropertiesGroupDescription() {
        Function<VariableManager, List<?>> semanticElementsProvider = variableManager -> Collections.singletonList(variableManager.getVariables().get(VariableManager.SELF));
        return this.getGroupDescription("groupId", "Core Properties", semanticElementsProvider);
    }

    private GroupDescription getSubsettingPropertiesGroupDescription() {
        Function<VariableManager, List<?>> semanticElementsProvider = variableManager -> {
            var self = variableManager.getVariables().get(VariableManager.SELF);
            if (self instanceof Element element) {
                return element.getOwnedRelationship().stream().filter(r -> SysmlPackage.eINSTANCE.getSubsetting().equals(r.eClass())).map(Subsetting.class::cast).toList();
            }
            return List.of();
        };
        return this.getGroupDescription("subsettingGroupId", "Subsetting Properties", semanticElementsProvider);
    }

    private GroupDescription getRedefinitionPropertiesGroupDescription() {
        Function<VariableManager, List<?>> semanticElementsProvider = variableManager -> {
            var self = variableManager.getVariables().get(VariableManager.SELF);
            if (self instanceof Feature feature) {
                return feature.getOwnedRedefinition();
            }
            return List.of();
        };
        return this.getGroupDescription("redefinitionGroupId", "Redefinition Properties", semanticElementsProvider);
    }

    private GroupDescription getFeatureTypingPropertiesGroupDescription() {
        Function<VariableManager, List<?>> semanticElementsProvider = variableManager -> {
            var self = variableManager.getVariables().get(VariableManager.SELF);
            if (self instanceof Usage usage) {
                return usage.getOwnedRelationship().stream().filter(FeatureTyping.class::isInstance).map(FeatureTyping.class::cast).toList();
            }
            return List.of();
        };
        return this.getGroupDescription("featureTypingGroupId", "Feature Typing Properties", semanticElementsProvider);
    }
}
