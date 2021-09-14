from django.http import HttpResponse
from rest_framework.generics import CreateAPIView, ListAPIView
from rest_framework.response import Response
from rest_framework.views import APIView
from testAPI.models import User
from testAPI.serializers import UserSerializer

# Create your views here.
def helloView(request):
    return HttpResponse("Hello, Spring. My Name is Django")

class UserListViewSet(ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

class UserCreateViewSet(CreateAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

# class UserViewSet(APIView):
#     def get(self, request):
#         serializer = UserSerializer(User.objects.all(), many=True)
#         return Response(serializer.data)
#
#     def post(self, request):
#         serializer = UserSerializer(data=request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status=201)
#         return Response(serializer.errors, status=400)