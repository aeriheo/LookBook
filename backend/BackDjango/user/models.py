# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class User(models.Model):
    user_email = models.CharField(primary_key=True, max_length=100)
    user_password = models.CharField(max_length=200)
    user_name = models.CharField(max_length=100)
    user_nickname = models.CharField(max_length=100)
    user_profile_url = models.CharField(max_length=200, blank=True, null=True)
    user_join_type = models.IntegerField()
    access_token = models.CharField(max_length=200, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'User'

