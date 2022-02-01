@extends('book.layouts.app')

@section('content')
<div class="card-header">{{ __('Ver Livro') }}</div>
<div class="col-lg-1"></div>
<div class="card-body">
<div class="col-lg-1">
    <a class="btn btn-primary" href="{{ url('book') }}"> Back</a>
</div>
    @if (session('status'))
        <div class="alert alert-success" role="alert">
            {{ session('status') }}
        </div>
    @endif
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
    {{ __('You are logged in!') }}
</div>
@endsection
