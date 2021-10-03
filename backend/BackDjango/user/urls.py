from django.urls import path

from . import views

urlpatterns = [
    path('', views.UserListViewSet.as_view(), name="UserListViewSet"),
    path('user/<user_email>', views.UserDetailListViewSet.as_view(), name="UserDetailListViewSet")
]