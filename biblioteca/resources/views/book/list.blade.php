@extends('book.layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">{{ __('Dashboard') }}</div>
                <div class="col-lg-1">
                </div>

                <div class="card-body">
                    @if (session('status'))
                        <div class="alert alert-success" role="alert">
                            {{ session('status') }}
                        </div>
                    @endif
                    <table class="table table-bordered">
                        <tr>
                            <th>No</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Address</th>
                            <th width="280px">Action</th>
                        </tr>
                        @php
                            $i = 0;
                        @endphp
                        @foreach ($books as $book)
                            <tr>
                                <td>{{ ++$i }}</td>
                                <td>{{ $book->first_name }}</td>
                                <td>{{ $book->last_name }}</td>
                                <td>{{ $book->address }}</td>
                                <td>
                                    <form action="{{ route('book.destroy',$book->id) }}" method="POST">
                                        <a class="btn btn-info" href="{{ route('book.show',$book->id) }}">Show</a>
                                        <a class="btn btn-primary" href="{{ route('book.edit',$book->id) }}">Edit</a>
                                        @csrf
                                        @method('DELETE')
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        @endforeach
                    </table>

                    

                    {{ __('You are logged in!') }}
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
