from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework.generics import ListAPIView
from user.models import User
from user.serializers import UserSerializer

from recommendation.generate import GetUserEmail


# Create your views here.
class UserListViewSet(ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer


from django.shortcuts import get_object_or_404


class UserDetailListViewSet(APIView):
    def get_object(self, user_email):
        return get_object_or_404(User, pk=user_email)

    def get(self, request, user_email, format=None):
        GetUserEmail().getUserEmail(user_email)
        return HttpResponse(user_email)
