from rest_framework import serializers
from recommendation.models import UserBasedCFModel, ItemBasedCFModel, UserPredictedGradeModel

class UserBasedCFSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserBasedCFModel
        fields = "__all__"

class ItemBasedCFSerializer(serializers.ModelSerializer):
    class Meta:
        model = ItemBasedCFModel
        fields = "__all__"

class UserPredictedGradeSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserPredictedGradeModel
        fields = "__all__"