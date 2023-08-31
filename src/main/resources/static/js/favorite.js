angular
  .module("favoriteApp", [])
  .controller("FavoritesController", function ($scope, $http) {

    // Categories list from API
    $scope.categories = [];

    // Filtered Categories
    $scope.realCategories = [];

    // Favorites list to display
    $scope.favorites = [];

    // Category filter
    $scope.categoryList = {
      filter: 0,
    };

    // Display mode : { view, creationFavorite, updateFavorite, creationCategory, updateCategory, categoryManagement }
    $scope.mode = "view";

    // Favorite item to create / update
    $scope.favorite = {};

    // Category item to create / update
    $scope.category = {};

    /* ----- INTERNAL STATE ----- */

    $scope.setMode = function (text) {
      if (text === "creationFavorite") {
        $scope.realCategories = $scope.categories.filter(function (c) {
          return c.id !== 0;
        });
        var idx = $scope.realCategories
          .map(function (c) {
            return c.id;
          })
          .indexOf($scope.categoryList.filter);
        if (idx < 0) idx = 0;

        $scope.favorite = {
          link: "",
          label: "",
          category: $scope.realCategories[idx].id,
        };
      } else if (text === "creationCategory") {
        $scope.category = {
          label: "",
        };
      }
      $scope.mode = text;
    };

    $scope.cancel = function () {
      $scope.setMode("view");
    };

    $scope.refresh = function () {
      $http.get("api/category").then(function (response) {
        $scope.categories = [{ id: 0, label: "Everything", references: 0 }];
        response.data.forEach((d) => {
          $scope.categories.push(d);
          $scope.categories[0].references += d.references;
        });

        $http.get("api/favorite").then(function (response) {
          $scope.favorites = response.data.filter(
            (f) =>
              $scope.categoryList.filter === 0 ||
              f.categoryDto.id === $scope.categoryList.filter
          );
        });
      });
    };

    /* ----- FAVORITES ----- */

    $scope.createFavorite = function () {
      $http
        .post("api/category/" + $scope.favorite.category + "/favorite", {
          id: null,
          label: $scope.favorite.label,
          link: $scope.favorite.link,
        })
        .then(
          function () {
            $scope.refresh();
            $scope.setMode("view");
          },
          function (error) {
            alert(error.data.message);
          }
        );
    };

    $scope.setUpdateFavorite = function (favorite) {
      $scope.realCategories = $scope.categories.filter(function (c) {
        return c.id !== 0;
      });
      var idx = $scope.realCategories
        .map(function (c) {
          return c.id;
        })
        .indexOf($scope.categoryList.filter);
      if (idx < 0) idx = 0;

      $scope.setMode("updateFavorite");
      $scope.favorite = {
        id: favorite.id,
        link: favorite.link,
        label: favorite.label,
        category: $scope.realCategories[idx].id,
      };
    };

    $scope.updateFavorite = function () {
      $http
        .post("api/category/" + $scope.favorite.category + "/favorite", {
          id: $scope.favorite.id,
          label: $scope.favorite.label,
          link: $scope.favorite.link,
        })
        .then(
          function () {
            $scope.refresh();
            $scope.setMode("view");
          },
          function (error) {
            alert(error.data.message);
          }
        );
    };

    $scope.deleteFavorite = function (id) {
      Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
      }).then((result) => {
        if (result.isConfirmed) {
          $http.delete("/api/favorite/" + id).then(
            function () {
              $scope.refresh();
            },
            function (error) {
              alert(error.data.message);
            }
          );
          Swal.fire("Deleted!", "Your file has been deleted.", "success");
        }
      });
    };

    /* ----- CATEGORIES ----- */

    $scope.createCategory = function () {
      $http
        .post("api/category", {
          id: null,
          label: $scope.category.label,
        })
        .then(
          function () {
            $scope.refresh();
            $scope.setMode("view");
          },
          function (error) {
            alert(error.data.message);
          }
        );
    };

    $scope.setUpdateCategory = function (category) {
      $scope.setMode("updateCategory");
      $scope.category = {
        id: category.id,
        label: category.label,
      };
    };

    $scope.updateCategory = function () {
      $http
        .post("api/category", {
          id: $scope.category.id,
          label: $scope.category.label,
        })
        .then(
          function () {
            $scope.refresh();
            $scope.setMode("view");
          },
          function (error) {
            alert(error.data.message);
          }
        );
    };

    $scope.deleteCategory = function (id) {
      Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!",
      }).then((result) => {
        if (result.isConfirmed) {
          $http.delete("/api/category/" + id).then(
            function () {
              $scope.refresh();
            },
            function (error) {
              alert(error.data.message);
            }
          );
          Swal.fire("Deleted!", "Your file has been deleted.", "success");
        }
      });
    };

    $scope.format = function (item) {
      return item.label + (item.id != 0 ? " (" + item.references + ")" : "");
    };

    $scope.refresh();
  });
