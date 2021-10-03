from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.generics import ListAPIView
from user.models import User
from user.serializers import UserSerializer
from recommendation.generate import UpdateUserBasedCF, UpdateUserPredictedGrade
from django.shortcuts import get_object_or_404


class UpdateUserRecommList(APIView):
    def get_object(self, user_email):
        return get_object_or_404(User, pk=user_email)

    def get(self, request, user_email, format=None):
        print("--- Update User Based CF ---")
        UpdateUserBasedCF(user_email).save_list()
        print("--- Update User Predicted Grade ---")
        UpdateUserPredictedGrade(user_email).save_list()
        return HttpResponse(user_email)
