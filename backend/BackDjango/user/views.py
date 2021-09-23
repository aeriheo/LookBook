from rest_framework.generics import ListAPIView
from user.models import User
from user.serializers import UserSerializer

# Create your views here.
class UserListViewSet(ListAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer
