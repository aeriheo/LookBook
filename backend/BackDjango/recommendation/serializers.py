from rest_framework import serializers
from recommendation.models import UserBasedCFModel, ItemBasedCFModel, UserPredictedGradeModel, BestBookTenModel

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

class BestBookTenSerializer(serializers.ModelSerializer):
    class Meta:
        model = BestBookTenModel
        fields = "__all__"
