from django.urls import path

from . import views

urlpatterns = [
    path('bookgrade/', views.BookGradeListViewSet.as_view(), name="BookGradeListViewSet")
]