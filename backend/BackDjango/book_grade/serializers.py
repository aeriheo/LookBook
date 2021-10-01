from rest_framework import serializers
from book_grade.models import BookGrade

class BookGradeSerializer(serializers.ModelSerializer):
    class Meta:
        model = BookGrade
        fields = "__all__"
