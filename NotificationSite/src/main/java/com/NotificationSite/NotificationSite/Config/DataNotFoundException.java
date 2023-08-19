package com.NotificationSite.NotificationSite.Config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DataNotFoundException(String message) {
        super(message);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 67257023daecb14042d018d611e43c9a109e638e
