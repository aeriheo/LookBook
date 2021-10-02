# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models

from book.models import Book
from user.models import User


class ItemBasedCFModel(models.Model):
    book_isbn = models.ForeignKey(Book, related_name="ItemBasedCFModel", on_delete=models.CASCADE, db_column='book_isbn')

    class Meta:
        managed = False
        db_table = 'ItemBasedCFModel'

class UserBasedCFModel(models.Model):
    user_email = models.ForeignKey(User, related_name="UserBasedCFModel", on_delete=models.CASCADE, db_column='user_email')
    book_isbn = models.ForeignKey(Book, related_name="UserBasedCFModel", on_delete=models.CASCADE, db_column='book_isbn')

    class Meta:
        managed = False
        db_table = 'UserBasedCFModel'

#
class UserPredictedGradeModel(models.Model):
    user_email = models.ForeignKey(User, related_name="UserPredictedGradeModel", on_delete=models.CASCADE, db_column='user_email')
    book_isbn = models.ForeignKey(Book, related_name="UserPredictedGradeModel", on_delete=models.CASCADE, db_column='book_isbn')

    class Meta:
        managed = False
        db_table = 'UserPredictedGradeModel'


class BestBookTenModel(models.Model):
    book_isbn = models.ForeignKey(Book, related_name="BestBookTen", on_delete=models.CASCADE, db_column='book_isbn')

    class Meta:
        managed = False
        db_table = 'BestBookTen'
