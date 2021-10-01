from django.db import models

class UserBasedCFModel(models.Model):
    user_email = models.CharField(max_length=100)
    book_isbn = models.CharField(max_length=13)

    class Meta:
        managed = False
        db_table = 'UserBasedCFModel'

class ItemBasedCFModel(models.Model):
    book_isbn = models.CharField(max_length=13)

    class Meta:
        managed = False
        db_table = 'ItemBasedCFModel'

class UserPredictedGradeModel(models.Model):
    user_email = models.CharField(max_length=100)
    book_isbn = models.CharField(max_length=13)

    class Meta:
        managed = False
        db_table = 'UserPredictedGradeModel'
