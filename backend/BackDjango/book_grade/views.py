from rest_framework.generics import ListAPIView
from book_grade.models import BookGrade
from book_grade.serializers import BookGradeSerializer

# Create your views here.
class BookGradeListViewSet(ListAPIView):
    queryset = BookGrade.objects.all()
    serializer_class = BookGradeSerializer
