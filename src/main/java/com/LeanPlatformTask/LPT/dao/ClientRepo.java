package com.LeanPlatformTask.LPT.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LeanPlatformTask.LPT.model.ClientModel;

public interface ClientRepo extends JpaRepository<ClientModel, Long>{

}
