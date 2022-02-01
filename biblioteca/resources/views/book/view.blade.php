@extends('book.layouts.app')

@section('content')
    <div class="row">
        <div class="col-lg-11">
                <h2>Laravel 8 CRUD Example</h2>
        </div>
        <div class="col-lg-1">
            <a class="btn btn-primary" href="{{ url('book') }}"> Back</a>
        </div>
    </div>
    <table class="table table-bordered">
        <tr>
            <th>Titulo:</th>
            <td>{{ $book->title }}</td>
        </tr>
        <tr>
            <th>Titulo em Inglês:</th>
            <td>{{ $book->titleEn }}</td>
        </tr>
        <tr>
            <th>Autor:</th>
            <td>{{ $book->author }}</td>
        </tr>
        <tr>
            <th>Edição:</th>
            <td>{{ $book->edition }}</td>
        </tr>
        <tr>
            <th>Editora:</th>
            <td>{{ $book->publisher }}</td>
        </tr>
        <tr>
            <th>Sinopse:</th>
            <td>{{ $book->synopse }}</td>
        </tr>
        <tr>
            <th>Genero:</th>
            <td>{{ $book->genders }}</td>
        </tr>
        <tr>
            <th>Quantidade:</th>
            <td>{{ $book->quantity }}</td>
        </tr>
        <tr>
            <th>Image:</th>
            <td>{{ $book->image }}</td>
        </tr>

    </table>
@endsection