package com.pq.service;

import com.pq.pojo.User;
import com.pq.utils.ResultContent;

public interface AdminService {
    ResultContent admin(User u);

    ResultContent update(User u);
}
