from django.db import models

# Create your models here.
class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    user_name = models.CharField(max_length=100)
    user_email = models.CharField(max_length=100)
    user_nickname = models.CharField(max_length=100)

    class Meta:
        db_table = u'MSATestData'

    def __str__(self):
        return f"{self.user_id}/{self.user_name}/{self.user_email}/{self.user_nickname}"
