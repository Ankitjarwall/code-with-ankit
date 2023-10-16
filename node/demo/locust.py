import time
from locust import HttpUser,task,between

class WebsiteUser(HttpUser):
    wait_time=between(1,5)
    
    @task
    def login(self):
        self.client.post("/api/v1/auth/user/login",{
            "email":"ayushsingh35323@gmail.com",
            "password":"1234five"
        })
    