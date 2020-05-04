package com.apatricio.demo.repository;

import com.apatricio.demo.model.Status;

public interface OrderRepositoryCustom {

    int findUpdateStatus(String id, Status taken, Status unassigned);

}
