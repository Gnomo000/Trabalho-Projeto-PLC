<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBooksTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('books', function (Blueprint $books) {
            $books->increments('id');
            $books->string('title');
            $books->string('titleEn');
            $books->string('author');
            $books->string('edition');
            $books->string('publisher');
            $books->string('synopse');
            $books->string('genders');
            $books->integer('quantity');
            $books->string('image');
            $books->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('books');
    }
}
