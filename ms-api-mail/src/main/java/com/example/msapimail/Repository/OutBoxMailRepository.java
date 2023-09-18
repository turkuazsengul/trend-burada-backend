package com.example.msapimail.Repository;

import com.example.msapimail.Entity.MailEntity;
import org.springframework.data.repository.CrudRepository;

public interface OutBoxMailRepository extends CrudRepository<MailEntity,Integer> {
}
