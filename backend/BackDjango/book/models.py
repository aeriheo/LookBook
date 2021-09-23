from django.db import models


# Create your models here.
class Book(models.Model):
    book_isbn = models.CharField(primary_key=True, max_length=13)
    book_title = models.TextField(null=False)
    book_author = models.TextField(null=False)
    book_pub = models.TextField()
    book_pub_date = models.CharField(max_length=10)
    # book_add_code = models.CharField(max_length=5)
    book_price = models.IntegerField()
    book_img_url = models.TextField()
    book_desc = models.TextField()
    # book_kdc = models.CharField(max_length=20)
    book_category_code = models.CharField(max_length=5)
    book_keyword1 = models.CharField(max_length=100)
    book_keyword2 = models.CharField(max_length=100)
    book_keyword3 = models.CharField(max_length=100)

    class Meta:
        db_table = u'Book'

    def __str__(self):
        return f"{self.book_isbn}/{self.book_title}/{self.book_author}/{self.book_pub}/{self.book_pub_date}/{self.book_add_code}/{self.book_price}/{self.book_img_url}/{self.book_desc}/{self.book_kdc}/{self.book_category_code}/{self.book_keyword1}/{self.book_keyword2}/{self.book_keyword3}"
