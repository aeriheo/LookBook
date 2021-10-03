from django.urls import path

from . import views

urlpatterns = [
    path('<user_email>', views.UpdateUserRecommList.as_view(), name="UpdateUserRecommList")
]