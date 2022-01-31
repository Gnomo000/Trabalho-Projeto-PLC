<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAppusersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('appusers', function (Blueprint $appusers) {
            $appusers->increments('id');
            $appusers->string('name');
            $appusers->string('date');
            $appusers->string('email');
            $appusers->string('phone');
            $appusers->string('username');
            $appusers->string('password');
            $appusers->string('image');
            $appusers->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('appusers');
    }
}
