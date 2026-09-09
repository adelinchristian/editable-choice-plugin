package io.jenkins.plugins.editable_choice;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import groovy.util.Node;
import groovy.xml.XmlUtil;
import java.util.Arrays;
import org.junit.Test;

public class EditableChoiceJobDslExtensionTest {

    @Test
    public void createsEditableChoiceParameterNode() {
        Node node = (Node) new EditableChoiceJobDslExtension().editableChoiceParam(
                "FERMI_INSTANCE",
                Arrays.asList("hw9-nad-sa515m", "hw9-nad-sa515m_CM4_C1.18_5G"),
                "hw9-nad-sa515m",
                "Fermi instance branch");

        String xml = XmlUtil.serialize(node);
        assertThat(xml, containsString("<name>FERMI_INSTANCE</name>"));
        assertThat(xml, containsString("hw9-nad-sa515m_CM4_C1.18_5G"));
        assertThat(xml, containsString("<defaultValue>hw9-nad-sa515m</defaultValue>"));
        assertThat(xml, containsString("<description>Fermi instance branch</description>"));
    }
}
