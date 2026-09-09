package io.jenkins.plugins.editable_choice;

import hudson.Extension;
import java.util.Collections;
import java.util.List;
import javaposse.jobdsl.dsl.helpers.BuildParametersContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;
import javaposse.jobdsl.plugin.DslExtensionMethod;
import groovy.util.Node;

/** Adds native Job DSL support for editable choice parameters. */
@Extension(optional = true)
public class EditableChoiceJobDslExtension extends ContextExtensionPoint {

    /**
     * Defines an editable choice parameter using the first choice as default.
     *
     * @param parameterName parameter name
     * @param choices predefined suggestions
     * @return parameter definition XML
     */
    @DslExtensionMethod(context = BuildParametersContext.class)
    public Object editableChoiceParam(String parameterName, List<String> choices) {
        return editableChoiceParam(parameterName, choices, null, null);
    }

    /**
     * Defines an editable choice parameter with a default value.
     *
     * @param parameterName parameter name
     * @param choices predefined suggestions
     * @param defaultValue initial value
     * @return parameter definition XML
     */
    @DslExtensionMethod(context = BuildParametersContext.class)
    public Object editableChoiceParam(
            String parameterName, List<String> choices, String defaultValue) {
        return editableChoiceParam(parameterName, choices, defaultValue, null);
    }

    /**
     * Defines an editable choice parameter with predefined suggestions.
     *
     * @param parameterName parameter name
     * @param choices predefined suggestions
     * @param defaultValue initial value
     * @param description parameter description
     * @return parameter definition XML
     */
    @DslExtensionMethod(context = BuildParametersContext.class)
    public Object editableChoiceParam(
            String parameterName, List<String> choices, String defaultValue, String description) {
        Node definitionNode = new Node(
                null, "io.jenkins.plugins.editable_choice.EditableChoiceParameterDefinition");
        definitionNode.appendNode("name", parameterName);
        Node choiceList = new Node(
            definitionNode,
            "choices",
            Collections.singletonMap("class", "java.util.Arrays$ArrayList"));
        for (String choice : choices) {
            new Node(choiceList, "a", Collections.singletonMap("class", "string"), choice);
        }
        if (defaultValue != null) {
            definitionNode.appendNode("defaultValue", defaultValue);
        }
        if (description != null) {
            definitionNode.appendNode("description", description);
        }
        return definitionNode;
    }
}
