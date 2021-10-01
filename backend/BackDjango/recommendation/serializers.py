from rest_framework import serializers
from recommendation.models import UserBasedCFModel

class UserBasedCFSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserBasedCFModel
        fields = "__all__"
