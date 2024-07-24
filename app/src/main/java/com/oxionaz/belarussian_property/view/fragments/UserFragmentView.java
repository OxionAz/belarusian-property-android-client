package com.oxionaz.belarussian_property.view.fragments;

import com.oxionaz.belarussian_property.model.source.rest.dto.UserDTO;
import com.oxionaz.belarussian_property.view.View;

public interface UserFragmentView extends View {

   void showMainActivity(UserDTO data);
}
