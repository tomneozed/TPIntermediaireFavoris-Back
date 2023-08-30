angular.module('favoriteApp', [])
    .controller('FavoritesController', function($scope, $http) {

        $scope.categories = [];
        $scope.realCategories = [];
        $scope.favorites = [];

        $scope.category = {
            filter: 0
        };

        $scope.mode = 'view';

        $scope.favorite = {};

        $scope.setMode = function(text) {
            if (text === 'creation') {
                $scope.realCategories = $scope.categories.filter(function(c) { return c.id !== 0 });
                var idx = $scope.realCategories.map(function(c) { return c.id }).indexOf($scope.category.filter);
                if (idx < 0) idx = 0;

                $scope.favorite = {
                    link: '',
                    label: '',
                    category: $scope.realCategories[idx].id
                }
            }
            $scope.mode = text;
        }

        $scope.cancel = function() {
            $scope.setMode('view');
        }

        $scope.setUpdate = function(favorite) {
            $scope.realCategories = $scope.categories.filter(function(c) { return c.id !== 0 });
            var idx = $scope.realCategories.map(function(c) { return c.id }).indexOf($scope.category.filter);
            if (idx < 0) idx = 0;

            $scope.setMode('update');
            $scope.favorite = {
                id: favorite.id,
                link: favorite.link,
                label: favorite.label,
                category: $scope.realCategories[idx].id
            };
            console.log($scope.favorite);
        }

        $scope.deleteFavorite = function(id) {
                Swal.fire({
                  title: 'Are you sure?',
                  text: "You won't be able to revert this!",
                  icon: 'warning',
                  showCancelButton: true,
                  confirmButtonColor: '#3085d6',
                  cancelButtonColor: '#d33',
                  confirmButtonText: 'Yes, delete it!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        $http.delete('/api/favorite/' + id).then(
                            function() {
                                $scope.refresh();
                            }, function(error) {
                                alert(error.data.message);
                            }
                        )
                        Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                        )
                    }
                })}


        $scope.validate = function() {
            $http.post('api/category/' + $scope.favorite.category + '/favorite' , {
                    id: null,
                    label: $scope.favorite.label,
                    link: $scope.favorite.link
                }).then(
                    function() {
                        $scope.refresh();
                        $scope.setMode('view');
                    }, function(error) {
                        alert(error.data.message);
                    }
            )
        }

        $scope.refresh = function() {
            $http.get('api/category').then(
                function(response) {
                    $scope.categories = [{id: 0, label: "Everything", references: 0}];
                    response.data.forEach(d => {
                        $scope.categories.push(d);
                        $scope.categories[0].references += d.references;
                    })

                    $http.get('api/favorite').then(
                        function(response) {
                            $scope.favorites = response.data.filter(
                                (f) =>
                                    $scope.category.filter === 0 ||
                                    f.categoryDto.id === $scope.category.filter);
                        }
                    )
                }
            )
        }

        $scope.update = function() {
            $http.post('api/category/' + $scope.favorite.category + '/favorite' , {
                 id: $scope.favorite.id,
                 label: $scope.favorite.label,
                 link: $scope.favorite.link
            }).then(
                function() {
                    $scope.refresh();
                    $scope.setMode('view');
                }, function(error) {
                    alert(error.data.message);
                }
            )
        }

        $scope.format = function(item) {
            return (item.label + (item.id != 0 ? ' (' + item.references + ')' : ''));
        }

        $scope.refresh();
    });