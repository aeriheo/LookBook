from django.http import HttpResponse
from rest_framework.generics import CreateAPIView, ListAPIView
from book.models import Book
from book.serializers import BookSerializer

# Create your views here.
class BookListViewSet(ListAPIView):
    queryset = Book.objects.all()
    serializer_class = BookSerializer
