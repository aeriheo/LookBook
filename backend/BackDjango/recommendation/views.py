from django.http import HttpResponse
from rest_framework.views import APIView
from user.models import User
from recommendation.generate import UpdateUserBasedCF, UpdateUserPredictedGrade
from django.shortcuts import get_object_or_404


class UpdateUserRecommList(APIView):
    def get_object(self, user_email):
        return get_object_or_404(User, pk=user_email)

    def get(self, request, user_email, format=None):
        user_nickname = User.objects.get(user_email=user_email).user_nickname

        print("--- Update User Based CF ---")
        UpdateUserBasedCF(user_email).save_list()
        print("--- Update User Predicted Grade ---")
        UpdateUserPredictedGrade(user_email).save_list()

        return HttpResponse(user_nickname)
