# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Book(models.Model):
    book_isbn = models.CharField(primary_key=True, max_length=13)
    book_title = models.TextField()
    book_author = models.TextField()
    book_pub = models.TextField(blank=True, null=True)
    book_pub_date = models.CharField(max_length=10, blank=True, null=True)
    book_price = models.IntegerField(blank=True, null=True)
    book_img_url = models.TextField(blank=True, null=True)
    book_desc = models.TextField(blank=True, null=True)
    book_category_code = models.CharField(max_length=5, blank=True, null=True)
    book_keyword1 = models.CharField(max_length=100, blank=True, null=True)
    book_keyword2 = models.CharField(max_length=100, blank=True, null=True)
    book_keyword3 = models.CharField(max_length=100, blank=True, null=True)
    book_like_cnt = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'Book'

