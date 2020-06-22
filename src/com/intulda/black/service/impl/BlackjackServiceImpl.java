package com.intulda.black.service.impl;

import com.intulda.black.service.IBlackjackService;
import com.intulda.black.service.dao.BlackjackDAO;
import com.intulda.black.service.model.UserDTO;

public class BlackjackServiceImpl implements IBlackjackService {

    private BlackjackDAO blackJackDAO;

    public BlackjackServiceImpl() {
        this.blackJackDAO = new BlackjackDAO();
    }

    @Override
    public void betting(UserDTO userDTO) {

    }
}
