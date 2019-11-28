package com.userFront.dao;

import com.userFront.domain.Recipient;
import org.springframework.data.repository.CrudRepository;

public interface RecipientDao extends CrudRepository<Recipient,Long> {
    Recipient findByName(String recipientName);

    void deleteRecipientByName(String recipientName);
}
