# Generated by Django 3.2.7 on 2021-10-01 15:31

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('recommendation', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='ItemBasedCFModel',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('book_isbn', models.CharField(max_length=13)),
            ],
            options={
                'db_table': 'ItemBasedCFModel',
                'managed': False,
            },
        ),
        migrations.CreateModel(
            name='UserPredictedGrade',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user_email', models.CharField(max_length=100)),
                ('book_isbn', models.CharField(max_length=13)),
            ],
            options={
                'db_table': 'UserPredictedGrade',
                'managed': False,
            },
        ),
    ]