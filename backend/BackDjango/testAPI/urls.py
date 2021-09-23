from django.urls import path

from . import views

urlpatterns = [
    path('', views.helloView, name="helloView"),
    path('user/', views.UserListViewSet.as_view(), name="UserListViewSet"),
    path(r'user/create/', views.UserCreateViewSet.as_view(), name="UserCreateViewSet")
]