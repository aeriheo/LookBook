from django.urls import path

from . import views

urlpatterns = [
    path('book/', views.BookListViewSet.as_view(), name="BookListViewSet")
]