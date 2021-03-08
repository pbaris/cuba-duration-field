package gr.netmechanics.cuba.df.web.gui.xml.layouts.loaders;

import com.haulmont.cuba.gui.xml.layout.loaders.AbstractFieldLoader;
import gr.netmechanics.cuba.df.web.gui.components.DurationField;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Panos Bariamis (pbaris)
 */
public class DurationFieldLoader extends AbstractFieldLoader<DurationField> {

    @Override
    public void createComponent() {
        resultComponent = factory.create(DurationField.class);
        loadId(resultComponent, element);
    }

    @Override
    public void loadComponent() {
        super.loadComponent();

        String inputPrompt = element.attributeValue("inputPrompt");
        if (StringUtils.isNotBlank(inputPrompt)) {
            resultComponent.setInputPrompt(loadResourceString(inputPrompt));
        }
    }
}
